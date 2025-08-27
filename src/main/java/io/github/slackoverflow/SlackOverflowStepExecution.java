package io.github.slackoverflow;

import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContext;

import hudson.model.TaskListener;
import hudson.model.Run;

import javax.annotation.Nonnull;

public class SlackOverflowStepExecution extends SynchronousNonBlockingStepExecution<Void> {

    private static final long serialVersionUID = 1L;

    private final SlackOverflowStep step;

    public SlackOverflowStepExecution(SlackOverflowStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

//    @Override
//    protected Void run() throws Exception {
//        TaskListener listener = getContext().get(TaskListener.class);
//        if (listener != null) {
//            Run<?, ?> run = getContext().get(Run.class);
//            listener.getLogger().println(SlackOverflowConsoleNote.annotate(step.getMessage()));
//        }
//        return null;
//    }

    @Override
    protected Void run() throws Exception {
        TaskListener listener = getContext().get(TaskListener.class);
        if (listener != null) {
            SlackOverflowConsoleNote note = SlackOverflowConsoleNote.annotate(step.getMessage());
            note.encodeTo(listener.getLogger()); // call final method on instance
        }
        return null;
    }

}
