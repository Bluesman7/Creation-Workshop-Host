package org.area515.resinprinter.stl;

import java.util.Arrays;

public class Triangle3d implements Shape3d {
	private Point3d[] verticies;
	private Point3d normal;
	private double[] min = new double[3];
	private double[] max = new double[3];
	private double[] xSlopes = new double[3];
	private double[] xIntercepts = new double[3];
	private double[] ySlopes = new double[3];
	private double[] yIntercepts = new double[3];
	
	public Triangle3d(Point3d[] points, Point3d normal) {
		if (points.length != 3) {
			throw new IllegalArgumentException("A triangle must have exactly three verticies");
		}
		
		this.verticies = points;
		this.normal = normal;
		min[0] = Math.min(points[0].x, Math.min(points[1].x, points[2].x));
		max[0] = Math.max(points[0].x, Math.max(points[1].x, points[2].x));
		min[1] = Math.min(points[0].y, Math.min(points[1].y, points[2].y));
		max[1] = Math.max(points[0].y, Math.max(points[1].y, points[2].y));
		min[2] = Math.min(points[0].z, Math.min(points[1].z, points[2].z));
		max[2] = Math.max(points[0].z, Math.max(points[1].z, points[2].z));
		xSlopes[0] = (points[0].x - points[1].x) / (points[0].z - points[1].z);
		xSlopes[1] = (points[1].x - points[2].x) / (points[1].z - points[2].z);
		xSlopes[2] = (points[2].x - points[0].x) / (points[2].z - points[0].z);
		ySlopes[0] = (points[0].y - points[1].y) / (points[0].z - points[1].z);
		ySlopes[1] = (points[1].y - points[2].y) / (points[1].z - points[2].z);
		ySlopes[2] = (points[2].y - points[0].y) / (points[2].z - points[0].z);
		xIntercepts[0] = -(xSlopes[0] * points[0].z - points[0].x);
		xIntercepts[1] = -(xSlopes[1] * points[1].z - points[1].x);
		xIntercepts[2] = -(xSlopes[2] * points[2].z - points[2].x);
		yIntercepts[0] = -(ySlopes[0] * points[0].z - points[0].y);
		yIntercepts[1] = -(ySlopes[1] * points[1].z - points[1].y);
		yIntercepts[2] = -(ySlopes[2] * points[2].z - points[2].y);		
	}
	
	public double getMinZ() {
		return min[2];
	}	
	
	public double getMinY() {
		return min[1];
	}
	
	public double getMinX() {
		return min[0];
	}
	
	public boolean intersectsZ(double z) {
		return z >= min[2] && z <= max[2];
	}
	
	public Line3d getZIntersection(double z) {
		int currentPoint = 0;
		Point3d line[] = new Point3d[2];
		for (int t = 0; t < 3; t++) {
			double x = xSlopes[t] * z + xIntercepts[t];
			double y = ySlopes[t] * z + yIntercepts[t];
			
			if ((x <= verticies[t].x && x >= verticies[t<2?t+1:0].x ||
				 x >= verticies[t].x && x <= verticies[t<2?t+1:0].x) &&
				(y <= verticies[t].y && y >= verticies[t<2?t+1:0].y ||
				 y >= verticies[t].y && y <= verticies[t<2?t+1:0].y)) {
				if (currentPoint < 2) {
					line[currentPoint++] = new Point3d(x, y, z);
				} else {
					System.out.println("Three points on a triangle:" + this + " that intersect on one z plane:" + z);
				}
			}
		}
		
		return new Line3d(line[0], line[1], normal);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		result = prime * result + Arrays.hashCode(verticies);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle3d other = (Triangle3d) obj;
		if (normal == null) {
			if (other.normal != null)
				return false;
		} else if (!normal.equals(other.normal))
			return false;
		if (!Arrays.equals(verticies, other.verticies))
			return false;
		return true;
	}

	public String toString() {
		return Arrays.toString(verticies) + "@" + normal;
	}
}
