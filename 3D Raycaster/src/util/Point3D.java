package util;

public class Point3D {

	private double x_position;
	private double y_position;
	private double z_position;

	public Point3D(double xpos, double ypos, double zpos) {
		this.x_position = xpos;
		this.y_position = ypos;
		this.z_position = zpos;
	}

	public void setX(double xpos) {
		this.x_position = xpos;
	}
	public void setY(double ypos) {
		this.y_position = ypos;
	}
	public void setZ(double zpos) {
		this.z_position = zpos;
	}

	public double getX() {
		return this.x_position;
	}
	public double getY() {
		return this.y_position;
	}
	public double getZ() {
		return this.z_position;
	}

	public void move(Vector3D vec) {
		this.x_position += vec.getXOffset();
		this.y_position += vec.getYOffset();
		this.z_position += vec.getZOffset();
	}

	public Point3D copy() {
		return new Point3D(this.x_position, this.y_position, this.z_position);
	}

	public String toString() {
		return "This is a Point3D object at position: " + this.x_position + " X, " + this.y_position + " Y, and " + this.z_position + " Z.";
	}
}
