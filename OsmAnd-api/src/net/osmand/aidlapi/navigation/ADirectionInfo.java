package net.osmand.aidlapi.navigation;

import android.os.Bundle;
import android.os.Parcel;

import net.osmand.aidlapi.AidlParams;

public class ADirectionInfo extends AidlParams {

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
			float averageSpeed, int leftTime, long arrivalTime, int leftDistance) {
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
		readFromParcel(in);
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
	public int getLeftTime() {return leftTime;}
	public int getLeftDistance() {return leftDistance;}
	public long getArrivalTime() {return arrivalTime;}

	public void setName(String name) { this.name = name; }
	public void setAfterNextTurnName(String name) {this.afterNextTurnName = name; }
	public void setAfterNextTurnType(int turntype) {this.afterNextTurnType=turntype; }
	public void setAfterNextTurnDistance(int distance) {this.afterNextTurnDistance=distance;}

	public void setDestinationName(String name) {this.destinationName = name; }
	public void setAverageSpeed(float speed) {this.averageSpeed = speed; }


	public void setDistanceTo(int distanceTo) {
		this.distanceTo = distanceTo;
	}

	public void setTurnType(int turnType) {
		this.turnType = turnType;
	}

	public void setLeftSide(boolean leftSide) {
		isLeftSide = leftSide;
	}
	public void setLeftTime(int duration) { leftTime=duration;}
	public void setLeftDistance(int dist) { leftDistance=dist;}
	public void setArrivalTime(long time) { arrivalTime=time;}

	@Override
	protected void readFromBundle(Bundle bundle) {
		distanceTo = bundle.getInt("distanceTo");
		turnType = bundle.getInt("turnType");
		isLeftSide = bundle.getBoolean("isLeftSide");
		name = bundle.getString("name");

		afterNextTurnName = bundle.getString("afterNextTurnName");
		afterNextTurnDistance = bundle.getInt("afterNextTurnDistance");
		afterNextTurnType = bundle.getInt("afterNextTurnType");

		averageSpeed = bundle.getFloat("averageSpeed");
		destinationName = bundle.getString("destinationName");
		leftTime = bundle.getInt("leftTime");
		arrivalTime = bundle.getLong("arrivalTime");
		leftDistance = bundle.getInt("leftDistance");
	}

	@Override
	public void writeToBundle(Bundle bundle) {
		bundle.putInt("distanceTo", distanceTo);
		bundle.putInt("turnType", turnType);
		bundle.putBoolean("isLeftSide", isLeftSide);
		bundle.putString("name", name);

		bundle.putString("afterNextTurnName", afterNextTurnName);
		bundle.putInt("afterNextTurnDistance", afterNextTurnDistance);
		bundle.putInt("afterNextTurnType", afterNextTurnType);

		bundle.putFloat("averageSpeed", averageSpeed);
		bundle.putString("destinationName", destinationName);
		bundle.putInt("leftTime", leftTime);
		bundle.putLong("arrivalTime", arrivalTime);
		bundle.putInt("leftDistance", leftDistance);

	}
}