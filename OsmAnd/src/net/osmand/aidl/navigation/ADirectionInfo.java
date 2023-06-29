package net.osmand.aidl.navigation;

import android.os.Parcel;
import android.os.Parcelable;

public class ADirectionInfo implements Parcelable {

	private int distanceTo; //distance to next turn
	private int turnType; //turn type
	private boolean isLeftSide; //is movement left-sided
	private String name;

	private String afterNextTurnName;
	private int afterNextTurnType;
	private int afterNextTurnDistance;

	private String destinationName;
	private float averageSpeed;
	private int leftTime;
	private long arrivalTime;
	private int leftDistance;


	public ADirectionInfo(
			int distanceTo,
			int turnType,
			boolean isLeftSide,
			String name,
			String afterNextTurnName,
			int afterNextTurnType,
			int afterNextTurnDistance,
			String destinationName,
			float averageSpeed, int leftTime, int arrivalTime, int leftDistance) {
		this.distanceTo = distanceTo;
		this.turnType = turnType;
		this.isLeftSide = isLeftSide;
		this.name = name;
		this.afterNextTurnName = afterNextTurnName;
		this.afterNextTurnType = afterNextTurnType;
		this.afterNextTurnDistance = afterNextTurnDistance;
		this.destinationName = destinationName;
		this.averageSpeed = averageSpeed;
		this.leftTime = leftTime;
		this.arrivalTime = arrivalTime;
		this.leftDistance = leftDistance;
	}

	protected ADirectionInfo(Parcel in) {
		distanceTo = in.readInt();
		turnType = in.readInt();
		isLeftSide = in.readByte() != 0;
		name = in.readString();
		afterNextTurnName = in.readString();
		afterNextTurnDistance = in.readInt();
		afterNextTurnType = in.readInt();
		averageSpeed = in.readFloat();
		destinationName = in.readString();
		leftTime = in.readInt();
		arrivalTime = in.readLong();
		leftDistance = in.readInt();
	}

	public static final Creator<ADirectionInfo> CREATOR = new Creator<ADirectionInfo>() {
		@Override
		public ADirectionInfo createFromParcel(Parcel in) {
			return new ADirectionInfo(in);
		}

		@Override
		public ADirectionInfo[] newArray(int size) {
			return new ADirectionInfo[size];
		}
	};

	public int getDistanceTo() {
		return distanceTo;
	}

	public int getTurnType() {
		return turnType;
	}

	public boolean isLeftSide() {
		return isLeftSide;
	}

	public String getName() { return name; }
	public String getAfterNextTurnName() {return afterNextTurnName; }
	public int getAfterNextTurnType() {return afterNextTurnType; }
	public int getAfterNextTurnDistance() {return afterNextTurnDistance;}

	public String getDestinationName() {return destinationName; }
	public float getAverageSpeed() {return averageSpeed; }

	public void setDistanceTo(int distanceTo) {
		this.distanceTo = distanceTo;
	}

	public void setTurnType(int turnType) {
		this.turnType = turnType;
	}

	public void setLeftSide(boolean leftSide) {
		this.isLeftSide = leftSide;
	}

	public void setName(String name) { this.name = name; }
	public void setAfterNextTurnName(String name) {this.afterNextTurnName = name; }
	public void setAfterNextTurnType(int turntype) {this.afterNextTurnType=turntype; }
	public void setAfterNextTurnDistance(int distance) {this.afterNextTurnDistance=distance;}

	public void setDestinationName(String name) {this.destinationName = name; }
	public void setAverageSpeed(float speed) {this.averageSpeed = speed; }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(distanceTo);
		dest.writeInt(turnType);
		dest.writeByte((byte) (isLeftSide ? 1 : 0));
		dest.writeString(name);

		dest.writeString(afterNextTurnName);
		dest.writeInt(afterNextTurnDistance);
		dest.writeInt(afterNextTurnType);

		dest.writeFloat(averageSpeed);
		dest.writeString(destinationName);
		dest.writeInt(leftTime);
		dest.writeLong(arrivalTime);
		dest.writeInt(leftDistance);
	}


	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getLeftDistance() {
		return leftDistance;
	}

	public void setLeftDistance(int leftDistance) {
		this.leftDistance = leftDistance;
	}
}
