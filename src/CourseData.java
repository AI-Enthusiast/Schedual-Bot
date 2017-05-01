/**
 * ClassData represents the class, and all useful information that can aid in determining
 *
 * @author Cormac
 * @since 4/25/17
 */
public class CourseData {

    // FIELDS
    private String name;        // only used when printing the results
    private String course;      // only used when printing the results
    private String professor;   // only used when printing the results
    private int credits;
    private int availability;
    private int desirability;
    private Day[] days;
    private CourseData[] preReqs = null;

    // CONSTRUCTOR

    /**
     * Constructor for ClassData
     *
     * @param className    the name of the course (eg "Calculus II for engineers")
     * @param course       the department and the course number(eg "MATH 122")
     * @param professor    the course professor (eg "Chris Butler")
     * @param credits      the number of credit hours the class is worth (eg 3)
     * @param availability the course semester availability (eg 1 for spring, 0 for fall, 2 for both)
     * @param desirability the course desirability (eg 1-10)
     * @param days         the days the course is being taught
     * @param preReqs      the pre requisites to this class
     */
    public CourseData(String className, String course, String professor, int credits, int availability, int desirability,
                      Day[] days, CourseData[] preReqs) {
        this.name = className;
        this.course = course;
        this.professor = professor;
        this.credits = credits;
        this.availability = availability;
        this.desirability = desirability;
        this.days = days;
        if (preReqs.length != 0) { // if there are no pre requirements
            this.preReqs = preReqs;
        }
    }

    // WORKING METHODS

    /**
     * Prints out a string of the course info
     *
     * @return "Math 122: Calculus for Engineers   |   Chris Butler   |   []"
     */
    @Override
    public String toString() {
        String out;
        out = getCourse() + ": " + getName() + "\t|\t" + getProfessor() + "\t|\t";
        for (int index = 0; index < this.getDays().length - 1; index++) {
            out = out + "[" + this.getDays()[index] + "@ " +
                    this.getDays()[index].getStart() + " - " + getDays()[index].getStop() + "]\n";
        }
        return out;
    }

    /**
     * Checks to see if the inputed course matches this course
     *
     * @param courseData The course you want to compare to
     * @return boolean of if it's equal
     */
    private boolean equals(CourseData courseData) {
        /* if the courses have the same name, professor, date, and time*/
        return courseData.getCourse().equals(this.getCourse()) &&
                courseData.getProfessor().equals(this.getProfessor()) &&
                (equalDays(courseData) && equalTimes(courseData));
    }

    /**
     * Helper to equals to check if it's on the same day
     *
     * @param courseData The course you want to compare to
     * @return boolean of if it's equal
     */
    private boolean equalDays(CourseData courseData) {
        if (courseData.getDays().length == this.getDays().length) {
            /* the goal of the loop is to determine whether the days are equal */
            for (int index = 0; index < this.getDays().length - 1; index++) {
                if (!courseData.getDays()[index].equals(this.getDays()[index])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks to see if any of the days overlap
     *
     * @param courseData The course you want to compare to
     * @return boolean of if theres overlap
     */
    boolean overlap(CourseData courseData) {
        /* the goal of the loop is to determine whether the days are equal */
        for (int index = 0; index < this.getDays().length - 1; index++) {
            if (courseData.getDays()[index].overlap(this.getDays()[index])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper to equals to check if the times are the same
     *
     * @param courseData The course you want to compare to
     * @return boolean of if it's equal
     */
    private boolean equalTimes(CourseData courseData) {
            /* the goal of the loop is to determine wether the times are equal */
            for (int index = 0; index < this.getDays().length - 1; index++) {
                if (courseData.getDays()[index].getStart() != this.getDays()[index].getStart() ||
                        courseData.getDays()[index].getStop() != this.getDays()[index].getStop()) {
                    return false;
                }
            }
            return true;
    }

    // GETTERS / SETTERS
    private String getName() {
        return name;
    }

    private String getCourse() {
        return course;
    }

    private String getProfessor() {
        return professor;
    }

    int getCredits() {
        return credits;
    }

    int getAvailability() {
        return availability;
    }

    int getDesirability() {
        return desirability;
    }

    private Day[] getDays() {
        return days;
    }

    public void setDays(Day[] days) {
        this.days = days;
    }

    CourseData[] getPreReqs() {
        return preReqs;
    }

    private void setDesirability(int desirability) {
        this.desirability = desirability;
    }

    /**
     * A class to represent a day
     */
    private static class Day {
        // FIELDS
        private String day;
        private int start;
        private int stop;

        //CONSTRUCTOR

        /**
         * Creates a day
         *
         * @param day       the name of the day (eg: "mo", "tu", "we", "th", "fr", "sa", "su")
         * @param startTime the time the class starts (eg: 0830)
         * @param stopTime  the time the class stops
         */
        public Day(String day, int startTime, int stopTime) {
            this.day = day;
            this.start = startTime;
            this.stop = stopTime;
        }

        //WORKING METHODS

        /**
         * Checks to see if the day's and times are equal
         * @param day the day being compaired
         * @return whether they are equal or not
         */
        public boolean equals(Day day){
            return (this.getDay().equals(day.getDay()) && this.getStart() == day.getStart() &&
                    this.getStop() == day.getStart());
        }

        /**
         * Checks to see if the times overlap
         * @param day the day being compared
         * @return whether they overlap or not
         */
        public boolean overlap(Day day){
            return (this.getDay().equals(day.getDay()) &&
                    (this.getStart() > day.getStop() || this.getStop() < day.getStart()));
        }

        // GETTERS/SETTERS
        String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        int getStop() {
            return stop;
        }

        public void setStop(int stop) {
            this.stop = stop;
        }
    }
}