/**
 * A class to represent a semester
 *
 * @author Cormac
 * @since 4/25/17
 */
public class Semester {

    private CourseData couseLoad[];

    public Semester(CourseData[] courses) {
        this.couseLoad = courses;
    }

    /**
     * Adds course to the courseLoad for the sememster
     *
     * @param course the course being added
     */
    public void addCourse(CourseData course) {
        CourseData[] temp = new CourseData[getCouseLoad().length + 1]; // create a new temporary Course array
        System.arraycopy(getCouseLoad(), 0, temp, 0, getCouseLoad().length - 1); //copies array over
        temp[temp.length - 1] = course; // copies the desired course on the end
    }

    /**
     * Removes a course from the semester
     *
     * @param course the course being removed
     */
    public void removeCourse(CourseData course) {
        /* the goal of the loop is to search through the course array and remove the desiered course */
        for (int index = 0; index < getCouseLoad().length - 1; index++) {
            /* if the courese is found */
            if (getCouseLoad()[index].equals(course)) {
                CourseData[] temp = new CourseData[getCouseLoad().length - 1]; // create a new temporary Course array
                /* copy array up until course */
                for (int outdex = 0; outdex < index; index++) {
                    temp[outdex] = getCouseLoad()[outdex];
                }
                /* copy array after course */
                for (int outdex = index + 1; outdex < getCouseLoad().length - 1; outdex++) {
                    temp[outdex] = getCouseLoad()[outdex];
                }
                setCouseLoad(temp);
            }
        }
    }

    /**
     * Checks to see if there is over lap in the semester
     *
     * @return whether there is overlap or not
     */
    public boolean overlap() {
        for (int index = 0; index < getCouseLoad().length - 2; index++) {
            for (int outdex = index + 1; outdex < getCouseLoad().length - 1; index++) {
                if (getCouseLoad()[outdex].overlap(getCouseLoad()[index])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks to see if a course will overlap with the semester
     * @param course course being tested
     * @return
     */
    public boolean overlap(CourseData course) {
        for (int index = 0; index < getCouseLoad().length - 1; index++) {
            if (getCouseLoad()[index].overlap(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the overlapping courses
     *
     * @param left the left index from whence it is searching on
     * @return the overlapping courses
     */
    public CourseData findOverlap(int left) {
        for (int index = left; index < getCouseLoad().length - 1; index++) {
            if (getCouseLoad()[left].overlap(getCouseLoad()[index])) {
                return getCouseLoad()[index];
            }
            findOverlap(left + 1);
        }
        return null;
    }

    /**
     * Calculates the total credit hours being taken that semester;
     *
     * @return
     */
    public int creditHours() {
        int credits = 0;
        for (int index = 0; index < getCouseLoad().length - 1; index++) {
            credits = credits + getCouseLoad()[index].getCredits();
        }
        return credits;
    }


    CourseData[] getCouseLoad() {
        return couseLoad;
    }

    public void setCouseLoad(CourseData[] couseLoad) {
        this.couseLoad = couseLoad;
    }
}