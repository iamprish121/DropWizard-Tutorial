package com.company.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import com.company.dropwizard.resources.helloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;

public class dropwizardApplication extends Application<dropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<dropwizardConfiguration> bootstrap) {
        // TODO: application initialization
        EnvironmentVariableSubstitutor substitutor = new EnvironmentVariableSubstitutor(false);
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), substitutor)
        );
    }

    @Override
    public void run(final dropwizardConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new helloWorldResource());
        environment.healthChecks().register("Healthy", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
//                return Result.unhealthy("NOT READY");
            }
        });
        System.out.println("mySetting = " + configuration.getMySetting());
    }

}
