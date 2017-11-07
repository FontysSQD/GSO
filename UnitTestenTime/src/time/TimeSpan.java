package time;

/**
 * @author Frank Peeters, Nico Kuijpers
 * <p>
 * LET OP: De klasse TimeSpan bevat enkele fouten.
 */
public class TimeSpan implements ITimeSpan {
    private ITime bt, et;

    public TimeSpan(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }
        else{
            this.bt = bt;
            this.et = et;
        }
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return et;
    }

    @Override
    public int length() {
        return bt.difference(et);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + et);
        }
        // Added "else"
        else {
            bt = beginTime;
        }
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) >= 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + bt);
        }
        // Added "else"
        else {
            et = endTime;
        }
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        et = et.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        // Added "else"
        else{
            et = et.plus(minutes);
        }
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getEndTime()) >= 0
                && timeSpan.getBeginTime().compareTo(getEndTime()) >= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }

        ITime beginTime, endTime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            beginTime = bt;
        }
        else {
            beginTime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endTime = et;
        }
        else {
            endTime = timeSpan.getEndTime();
        }

        return new TimeSpan(beginTime, endTime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        }
        else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        }
        else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan(begintime, endtime);
    }
}
