package io.github.slackoverflow;

import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleNote;
import hudson.MarkupText;

import java.io.IOException;
import java.io.PrintStream;

/**
 * A ConsoleNote that injects a modal into the Jenkins console output.
 */
public class SlackOverflowConsoleNote extends ConsoleNote<Object> {

    private final String message;

    public SlackOverflowConsoleNote(String message) {
        this.message = message;
    }

    @Override
    public ConsoleAnnotator annotate(Object context, MarkupText text, int charPos) {
        String script = "<script>" +
                "if (!document.getElementById('slackOverflowModal')) {" +
                "  var modal = document.createElement('div');" +
                "  modal.id = 'slackOverflowModal';" +
                "  modal.style.position = 'fixed';" +
                "  modal.style.top = '20%';" +
                "  modal.style.right = '20px';" +
                "  modal.style.width = '400px';" +
                "  modal.style.padding = '20px';" +
                "  modal.style.background = 'white';" +
                "  modal.style.boxShadow = '0px 0px 10px rgba(0,0,0,0.5)';" +
                "  modal.style.zIndex = '9999';" +
                "  modal.innerHTML = '" + message.replace("'", "\\'") + "';" +
                "  var overlay = document.createElement('div');" +
                "  overlay.id = 'slackOverflowOverlay';" +
                "  overlay.style.position = 'fixed'; overlay.style.top='0'; overlay.style.left='0';" +
                "  overlay.style.width='100%'; overlay.style.height='100%';" +
                "  overlay.style.background='rgba(0,0,0,0.2)';" +
                "  overlay.style.zIndex='9998';" +
                "  overlay.onclick = function() {" +
                "    document.body.removeChild(modal);" +
                "    document.body.removeChild(overlay);" +
                "  };" +
                "  document.body.appendChild(overlay);" +
                "  document.body.appendChild(modal);" +
                "}" +
                "</script>";
        text.addMarkup(text.length(), text.length(), script, "");
        return null;
    }



    public static SlackOverflowConsoleNote annotate(String message) {
        return new SlackOverflowConsoleNote(message);
    }

}