package time;

/**
 * UnitTestenTime Created by Sven de Vries on 27-9-2017
 */
public class TimeSpan2 implements ITimeSpan {
    private ITime bt;
    private long timeSpan;

    public TimeSpan2(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        } else {
            this.bt = bt;
            this.timeSpan = bt.difference(et);
        }
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        ITime time = new Time(bt.getYear(),bt.getMonth(),bt.getDay(),bt.getHours(),bt.getMinutes());
        return time.plus((int)timeSpan);
    }

    @Override
    public int length() {
        return (int)timeSpan;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        ITime endTime = getEndTime();
        if (beginTime.compareTo(endTime) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + endTime);
        }
        else {
            bt = beginTime;
        }
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) >= 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + bt);
        } else {
            timeSpan = bt.difference(endTime);
        }
    }

    @Override
    public void move(int minutes) {
        bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        } else {
            timeSpan += minutes;
        }
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || getEndTime().compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (getEndTime().compareTo(timeSpan.getEndTime()) < 0) {
            endtime = getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan2(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (getEndTime().compareTo(timeSpan.getEndTime()) > 0) {
            endtime = getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan2(begintime, endtime);
    }
}
