package de.dfki.vsm.api;

import de.dfki.vsm.util.log.LOGDefaultLogger;
import java.io.Closeable;

/**
 * @author Gregor Mehlmann
 */
public abstract class VSMAgentClient extends Thread {

    // The Singelton Logger Instance
    protected final LOGDefaultLogger mVSM3Log = LOGDefaultLogger.getInstance();
    // The Thread Termination Flag
    protected volatile boolean mDone = false;
    // The Scene Player
    protected final VSMScenePlayer mPlayer;

    // The Agent Features
    protected final String mAgentName;
    protected final String mAgentUaid;
    protected final String mRemoteHost;
    protected final int mRemotePort;

    // The Closeable
    protected Closeable mClosable;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public VSMAgentClient(
            final VSMScenePlayer player,
            final String name, final String uaid,
            final String host, final int port) {

        // Initialize The Client
        mPlayer = player;
        // Initialize The Fields
        mAgentName = name;
        mAgentUaid = uaid;
        mRemoteHost = host;
        mRemotePort = port;
        // Debug Some Information
        mVSM3Log.message("Creating VSM Agent Client For '" + name + "' With Id '" + uaid + "' On '" + host + ":" + port + "'");
    }

    public final String getAgentName() {
        return mAgentName;
    }

    public final String getAgentUaid() {
        return mAgentUaid;
    }

    public final String getRemoteHost() {
        return mRemoteHost;
    }

    public final int getRemotePort() {
        return mRemotePort;
    }

    @Override
    public abstract void run();

    public abstract void abort();

    public abstract boolean sendBytes(final byte[] bytes);

    public abstract boolean sendString(final String string);

    public abstract byte[] recvBytes(final int size);

    public abstract String recvString();
}
