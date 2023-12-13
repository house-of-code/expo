package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;

/**
 * A schedulable trigger representing a notification to be scheduled once per year.
 */
public class MonthlyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
  private int mDay;
  private int mMonth;
  private int mHour;
  private int mMinute;
  private int mRepeatAmount;

  public MonthlyTrigger(int day, int month, int hour, int minute, int repeatAmount, @Nullable String channelId) {
    super(channelId);
    mDay = day;
    mMonth = month;
    mHour = hour;
    mMinute = minute;
    mRepeatAmount = repeatAmount;
  }

  private MonthlyTrigger(Parcel in) {
    super(in);
    mDay = in.readInt();
    mMonth = in.readInt();
    mHour = in.readInt();
    mMinute = in.readInt();
    mRepeatAmount = in.readInt();
  }

  public int getDay() {
    return mDay;
  }

  public int getMonth() {
    return mMonth;
  }

  public int getHour() {
    return mHour;
  }

  public int getMinute() {
    return mMinute;
  }

  public int getRepeatAmount() {
    return mRepeatAmount;
  }

  @Nullable
  @Override
  public Date nextTriggerDate() {
    Calendar nextTriggerDate = Calendar.getInstance();
    nextTriggerDate.set(Calendar.DATE, mDay);
    nextTriggerDate.set(Calendar.MONTH, mMonth);
    nextTriggerDate.set(Calendar.HOUR_OF_DAY, mHour);
    nextTriggerDate.set(Calendar.MINUTE, mMinute);
    nextTriggerDate.set(Calendar.SECOND, 0);
    nextTriggerDate.set(Calendar.MILLISECOND, 0);
    Calendar rightNow = Calendar.getInstance();
    if (nextTriggerDate.before(rightNow)) {
      nextTriggerDate.add(Calendar.MONTH, mRepeatAmount);
    }
    return nextTriggerDate.getTime();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeInt(mDay);
    dest.writeInt(mMonth);
    dest.writeInt(mHour);
    dest.writeInt(mMinute);
    dest.writeInt(mRepeatAmount);
  }

  public static final Creator<MonthlyTrigger> CREATOR = new Creator<MonthlyTrigger>() {
    @Override
    public MonthlyTrigger createFromParcel(Parcel in) {
      return new MonthlyTrigger(in);
    }

    @Override
    public MonthlyTrigger[] newArray(int size) {
      return new MonthlyTrigger[size];
    }
  };
}
