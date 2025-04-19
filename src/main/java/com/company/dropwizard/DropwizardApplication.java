package com.company.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import com.company.dropwizard.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;

public class DropwizardApplication extends Application<DropwizardConfiguration>{

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
        EnvironmentVariableSubstitutor substitutor = new EnvironmentVariableSubstitutor(false);
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), substitutor)
        );

        // Serve everything in /assets at the root ("/")
//        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(final DropwizardConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new HelloWorldResource());
        environment.healthChecks().register("Healthy", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
//                return Result.unhealthy("NOT READY");
            }
        });
        System.out.println("mySetting = " + configuration.getMySetting());
//        Bundle
        System.out.println("Bundle config value: " + configuration.getBundleSpecificConfig());

    }

}
