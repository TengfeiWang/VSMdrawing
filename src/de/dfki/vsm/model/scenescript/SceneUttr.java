package de.dfki.vsm.model.scenescript;

//~--- non-JDK imports --------------------------------------------------------

import de.dfki.vsm.util.ios.IOSIndentWriter;
import de.dfki.vsm.util.xml.XMLParseAction;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLWriteError;

import org.w3c.dom.Element;

//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Not me
 */
public class SceneUttr extends SceneEntity {

    // The Word List
    private LinkedList<AbstractWord> mWordList = new LinkedList<>();

    // The Punctuation
    private String mPunct;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public SceneUttr() {}

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public SceneUttr(final int lower, final int upper, final LinkedList<AbstractWord> list, final String punct) {

        // Initialize Boundary
        super(lower, upper);

        // Initialize Members
        mWordList = list;
        mPunct    = punct;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final LinkedList<AbstractWord> getWordList() {
        return mWordList;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final void setWordList(final LinkedList<AbstractWord> list) {
        mWordList = list;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final LinkedList<AbstractWord> copyWordList() {

        // Construct A List Copy
        final LinkedList<AbstractWord> copy = new LinkedList<>();

        // Copy Each Single Member
        for (final AbstractWord word : mWordList) {
            copy.add(word.getCopy());
        }

        // Return The Final Clone
        return copy;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final String getPunct() {
        return mPunct;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final void setPunct(final String punct) {
        mPunct = punct;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final String getText() {
        String result = "";

        for (int i = 0; i < mWordList.size(); i++) {
            result += " " + mWordList.get(i).getText();
        }

        return result + mPunct;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final String getText(final HashMap<String, String> args) {
        String result = "";

        for (int i = 0; i < mWordList.size(); i++) {
            result += " " + mWordList.get(i).getText(args);
        }

        return result + mPunct;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final void writeXML(final IOSIndentWriter stream) throws XMLWriteError {
        stream.println("<SceneUttr " + "lower=\"" + mLower + "\" " + "upper=\"" + mUpper + "\" " + "punct=\"" + mPunct
                       + "\">");
        stream.push();

        for (final AbstractWord word : mWordList) {
            word.writeXML(stream);

            if (!word.equals(mWordList.getLast())) {
                stream.endl();
            }
        }

        stream.pop();
        stream.endl();
        stream.print("</SceneUttr>");
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final void parseXML(final Element element) throws XMLParseError {

        // Parse The Boundary
        mLower = Integer.parseInt(element.getAttribute("lower"));
        mUpper = Integer.parseInt(element.getAttribute("upper"));

        // Parse Punctuation
        mPunct = element.getAttribute("punct");

        // Process The Child Nodes
        XMLParseAction.processChildNodes(element, new XMLParseAction() {
            @Override
            public void run(final Element element) throws XMLParseError {

                // Get The Child Tag Name
                final String name = element.getTagName();

                // Check The Child Tag Name
                if (name.equals("SceneWord")) {

                    // Create A New Simple Word
                    final SceneWord word = new SceneWord();

                    // Parse The NewSimple Word
                    word.parseXML(element);

                    // Add The New Simple Word
                    mWordList.add(word);
                } else if (name.equals("SceneAbbrev")) {

                    // Create A New Simple Word
                    final SceneAbbrev word = new SceneAbbrev();

                    // Parse The NewSimple Word
                    word.parseXML(element);

                    // Add The New Simple Word
                    mWordList.add(word);
                } else if (name.equals("SceneParam")) {

                    // Create A New Simple Word
                    final SceneParam word = new SceneParam();

                    // Parse The NewSimple Word
                    word.parseXML(element);

                    // Add The New Simple Word
                    mWordList.add(word);
                } else if (name.equals("ActionObject")) {

                    // Create A New Simple Word
                    final ActionObject word = new ActionObject();

                    // Parse The NewSimple Word
                    word.parseXML(element);

                    // Add The New Simple Word
                    mWordList.add(word);
                } else {

                    // Do Nothing
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final SceneUttr getCopy() {
        return new SceneUttr(mLower, mUpper, copyWordList(), mPunct);
    }
}
