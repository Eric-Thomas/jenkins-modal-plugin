package io.github.slackoverflow;

import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

import java.io.Serializable;

public class SlackOverflowStep extends AbstractStepImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;

    @DataBoundConstructor
    public SlackOverflowStep(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public StepExecution start(StepContext context) {
        return new SlackOverflowStepExecution(this, context);
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(SlackOverflowStepExecution.class);
        }

        @Override
        public String getFunctionName() {
            return "slackOverflow";
        }

        @Override
        public String getDisplayName() {
            return "Slack Overflow Modal";
        }
    }
}
