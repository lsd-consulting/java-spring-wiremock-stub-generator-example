package io.lsdconsulting.stub.component;

import io.cucumber.junit.platform.engine.Cucumber;

/**
 * <p>
 * This annotation helps Gradle with JUnit5 to find the feature files.
 * </p>
 * <p>It will look in the same package as this class when scanning the build directory resources.</p>
 * <p>
 * To trigger the component tests use: <pre>{@code gradle componentTest}</pre>
 * (Executing this class in your IDE won't work but you can run
 * the gradle task or individual feature files directly).</p>
 */
@Cucumber
public class RunComponentTests {
}
