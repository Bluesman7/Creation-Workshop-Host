package org.area515.resinprinter.notification;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;

import org.area515.jackson.util.JacksonEncoder;
import org.area515.jackson.util.PrintJobJacksonDecoder;
import org.area515.resinprinter.display.InappropriateDeviceException;
import org.area515.resinprinter.job.PrintJob;
import org.area515.resinprinter.printer.Printer;

@ServerEndpoint(value="/printjobnotification/{printJobName}", encoders={JacksonEncoder.class}, decoders={PrintJobJacksonDecoder.class})
public class WebSocketPrintJobNotifier implements Notifier {
	private static ConcurrentHashMap<String, ConcurrentHashMap<String, Session>> sessionsByPrintJobName = new ConcurrentHashMap<String, ConcurrentHashMap<String, Session>>();
	
	public WebSocketPrintJobNotifier() {
		super();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("printJobName") String printerName) {
		ConcurrentHashMap<String, Session> sessionsBySessionId = new ConcurrentHashMap<String, Session>();
		sessionsBySessionId.put(session.getId(), session);
		ConcurrentHashMap<String, Session> otherSessionsBySessionId = sessionsByPrintJobName.putIfAbsent(printerName, sessionsBySessionId);
		if (otherSessionsBySessionId != null) {
			otherSessionsBySessionId.put(session.getId(), session);
		}
	}
	
	@OnClose
	public void onClose(Session session, @PathParam("printJobName") String printerName) {
		ConcurrentHashMap<String, Session> sessionsBySessionId = new ConcurrentHashMap<String, Session>();
		sessionsBySessionId.put(session.getId(), session);
		ConcurrentHashMap<String, Session> otherSessionsBySessionId = sessionsByPrintJobName.get(printerName);
		if (otherSessionsBySessionId != null) {
			otherSessionsBySessionId.remove(session);
		}
	}
	
	@Override
	public void register(ServerContainer container) throws InappropriateDeviceException {
		try {
			container.addEndpoint(WebSocketPrintJobNotifier.class);
		} catch (DeploymentException e) {
			throw new InappropriateDeviceException("Couldn't deploy", e);
		}
	}

	@Override
	public void jobChanged(PrintJob job) {
		ConcurrentHashMap<String, Session> sessionsBySessionId = sessionsByPrintJobName.get(job.getJobFile().getName());
		if (sessionsBySessionId == null) {
			return;
		}
		
		for (Session currentSession : sessionsBySessionId.values()) {
			try {
				currentSession.getAsyncRemote().sendObject(job);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void printerChanged(Printer printer) {
	}
	
	@Override
	public void stop() {
		for (ConcurrentHashMap<String, Session> sessions : sessionsByPrintJobName.values()) {
			for (Session currentSession : sessions.values()) {
				try {
					currentSession.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "The printer host has been asked to shut down now!"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
