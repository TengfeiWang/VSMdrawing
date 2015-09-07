package de.dfki.vsm.util.log;

//~--- non-JDK imports --------------------------------------------------------

import de.dfki.vsm.Preferences;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LOGNovaFileFormat extends Formatter {

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final String format(final LogRecord record) {

        // Create The Date Of Logging
        Date date = new Date(record.getMillis());

        // Create The Thread Of Logging
        Thread thread = Thread.currentThread();

        // Create The Name Of Logger
        String name = record.getLoggerName();

        // Create The Stack Trace
        Object[] trace = record.getParameters();

        // Create The Method Name
        Object method = trace[2];

        // Create The String For Logging
        String message = record.getLevel() + " to " + "NOVA" + " on " + date + " by " + name + " in " + thread + " at "
                         + method;

        // Append The User Message
        message += Preferences.sSYSPROPS_LINE_SEPR + record.getMessage()    // The Message
                   + Preferences.sSYSPROPS_LINE_SEPR + Preferences.sSYSPROPS_LINE_SEPR;

        // return The Final Log Message
        return message;
    }
}
