/**
 * A class to represent a semester
 *
 * @author Cormac
 * @since 4/25/17
 */
public class Semester {

    private CourseData courseLoad[];

    public Semester(CourseData[] courses) {
        this.courseLoad = courses;
    }

    /**
     * Adds course to the courseLoad for the sememster
     *
     * @param course the course being added
     */
    public void addCourse(CourseData course) {
        CourseData[] temp = new CourseData[getCourseLoad().length + 1]; // create a new temporary Course array
        System.arraycopy(getCourseLoad(), 0, temp, 0, getCourseLoad().length - 1); //copies array over
        temp[temp.length - 1] = course; // copies the desired course on the end
    }

    /**
     * Removes a course from the semester
     *
     * @param course the course being removed
     */
    public void removeCourse(CourseData course) {
        /* the goal of the loop is to search through the course array and remove the desiered course */
        for (int index = 0; index < getCourseLoad().length - 1; index++) {
            /* if the course is found */
            if (getCourseLoad()[index].equals(course)) {
                CourseData[] temp = new CourseData[getCourseLoad().length - 1]; // create a new temporary Course array
                /* copy array up until course */
                for (int outdex = 0; outdex < index; index++) {
                    temp[outdex] = getCourseLoad()[outdex];
                }
                /* copy array after course */
                for (int outdex = index + 1; outdex < getCourseLoad().length - 1; outdex++) {
                    temp[outdex] = getCourseLoad()[outdex];
                }
                setCourseLoad(temp);
            }
        }
    }

    /**
     * Checks to see if there is over lap in the semester
     *
     * @return whether there is overlap or not
     */
    public boolean overlap() {
        for (int index = 0; index < getCourseLoad().length - 2; index++) {
            for (int outdex = index + 1; outdex < getCourseLoad().length - 1; index++) {
                if (getCourseLoad()[outdex].overlap(getCourseLoad()[index])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks to see if a course will overlap with the semester
     *
     * @param course course being tested
     * @return
     */
    public boolean overlap(CourseData course) {
        for (int index = 0; index < getCourseLoad().length - 1; index++) {
            if (getCourseLoad()[index].overlap(course)) {
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
        for (int index = left; index < getCourseLoad().length - 1; index++) {
            if (getCourseLoad()[left].overlap(getCourseLoad()[index])) {
                return getCourseLoad()[index];
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
        for (int index = 0; index < getCourseLoad().length - 1; index++) {
            credits = credits + getCourseLoad()[index].getCredits();
        }
        return credits;
    }


    CourseData[] getCourseLoad() {
        return courseLoad;
    }

    public void setCourseLoad(CourseData[] courseLoad) {
        this.courseLoad = courseLoad;
    }
}