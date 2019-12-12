package day12;

public class Moon {
	private int x;
	private int y;
	private int z;

	private int vX;
	private int vY;
	private int vZ;

	public Moon() {

	}

	public Moon(Moon moon) {
		this.x = moon.x;
		this.y = moon.y;
		this.z = moon.z;
		this.vX = moon.vX;
		this.vY = moon.vY;
		this.vZ = moon.vZ;
	}

	public void move() {
		x += vX;
		y += vY;
		z += vZ;
	}

	public long getTotalEnergy() {
		long potEn = Math.abs(x) + Math.abs(y) + Math.abs(z);
		long kinEn = Math.abs(vX) + Math.abs(vY) + Math.abs(vZ);

		return potEn * kinEn;
	}

	public boolean sameState(Moon moon) {
		return x == moon.x && y == moon.y && z == moon.z && vX == moon.vX && vY == moon.vY && vZ == moon.vZ;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public int getvZ() {
		return vZ;
	}

	public void setvZ(int vZ) {
		this.vZ = vZ;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Moon [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append(", vX=");
		builder.append(vX);
		builder.append(", vY=");
		builder.append(vY);
		builder.append(", vZ=");
		builder.append(vZ);
		builder.append("]");
		return builder.toString();
	}

}
