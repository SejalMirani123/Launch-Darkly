package com.launchdarkly.tutorial.examples;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public  class ApiController {
    static final String SDK_KEY = "sdk-fd7ba1ec-aa09-43a5-bc92-fcd0cdf592dc";

    static final String FEATURE_FLAG_KEY = "demo-123";
    @GetMapping("/feature-flag")
    public String getFeatureFlag() throws IOException {
        LDConfig config = new LDConfig.Builder().build();
        LDClient client = new LDClient(SDK_KEY, config);

        if (client.isInitialized()) {
            LDContext context = LDContext.builder("example-user-key")
                    .name("Sandy")
                    .build();

            boolean flagValue = client.boolVariation(FEATURE_FLAG_KEY, context, false);

            client.close();

            return "Feature flag '" + FEATURE_FLAG_KEY + "' is " + flagValue + " for this context";
        } else {
            return "SDK failed to initialize";
        }
    }
}

