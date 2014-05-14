package mooklabs.mookcore;

import java.lang.annotation.Documented;

/**This method is unused in some way, I may implement it later, or may be implemented later by forge. <br> ie biomes have not be implemented by forge in 1.7*/
@Documented
public @interface Unused {
    /**
     * Reason for unused status
     */
    String reason() default "";
    /**
     * just some notes if you want!
     */
    String notes() default "";
	
}
