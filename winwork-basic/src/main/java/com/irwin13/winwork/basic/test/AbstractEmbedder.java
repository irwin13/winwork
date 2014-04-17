package com.irwin13.winwork.basic.test;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

/**
 * @author irwin Timestamp : 23/01/13 20:21
 */
public abstract class AbstractEmbedder extends JUnitStories {

    public AbstractEmbedder() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(false).doVerboseFailures(true).useThreads(1).useStoryTimeoutInSecs(120);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE))
                .usePendingStepStrategy(new FailingUponPendingStep());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepsList());
    }

    @Override
    protected List<String> storyPaths() {
        String codeLocation = codeLocationFromPath("src/test/resources").getFile();
        return new StoryFinder().findPaths(codeLocation, asList(storyFilter()), null);
    }

    public abstract List<Object> stepsList();
    public abstract String storyFilter();

}
