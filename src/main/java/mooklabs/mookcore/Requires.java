package mooklabs.mookcore;

import java.lang.annotation.Documented;

/** This method is unused in some way, I may implement it later, or may be implemented later by forge. */
@Documented
public @interface Requires {
    /**
     * What it requires
     */
    Class[] required();
    /**
     * just some notes if you want!
     */
    String notes() default "";
    /**
     * If I don't want it to requre it
     */
    boolean removeRequirement() default false;

}
