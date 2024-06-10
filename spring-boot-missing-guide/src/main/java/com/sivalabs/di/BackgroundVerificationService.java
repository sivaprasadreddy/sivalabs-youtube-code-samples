package com.sivalabs.di;

import org.springframework.stereotype.Component;

/**
 * Verifies the loan requester credibility score by talking to an external 3rd party service.
 */
@Component
public class BackgroundVerificationService implements IBackgroundVerificationService {
    /**
     * Calls 3rd party paid service to get credibility score
     *
     * @return score on the scale of 1 to 5 where 1 is best score and 5 being worst
     */
    @Override
    public int getScore(String personUniqueId) {
        //talk to 3rd party
        return 2;
    }
}
