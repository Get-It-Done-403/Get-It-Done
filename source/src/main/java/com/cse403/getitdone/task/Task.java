import java.util.Date;

public class Task {
    private String title;
    private Date dueDate;
    private int hoursToComplete;
    private boolean isCompleted;

    public Task(final String title, final Date dueDate, final int hoursToComplete) {
        this.title = title;
        this.dueDate = dueDate;
        this.hoursToComplete = hoursToComplete;
        this.isCompleted = false;
    }

    public Task(final String title, final int year, final int month, final int date, final int hrs, final int min) {
        this.title = title;
        this.dueDate = new Date(year, month, date, hrs, min);
        this.hoursToComplete = null;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(final int year, final int month, final int date, final int hrs, final int min) {
        this.dueDate = new Date(year, month, date, hrs, min);
    }

    public int getHoursToComplete() {
        return hoursToComplete;
    }

    public void setHoursToComplete(final int hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(final boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.title) + this.dueDate.hashCode() *
                Integer.valueOf(this.hoursToComplete);
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Task)) {
            return false;
        } else {
            Task e = (Task) o;
            return this.title.equals(e.title)
                    && this.dueDate.equals(e.dueDate)
                    && this.hoursToComplete(e.hoursToComplete)
                    && this.isCompleted(e.isCompleted);
        }
    }

    @Override
    public String toString() {
        return String.format("Task [title=%s, dueDate=%s, hoursToComplete=%s, isCompleted]",
                title, this.dueDate.toString(), hoursToComplete, isCompleted);
    }

}