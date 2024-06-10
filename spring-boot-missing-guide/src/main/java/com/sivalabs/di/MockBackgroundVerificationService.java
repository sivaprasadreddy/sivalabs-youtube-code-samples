package com.sivalabs.di;

import java.util.List;

public class MockBackgroundVerificationService implements IBackgroundVerificationService{
    List<String> approvedUniqueIds = List.of(
            "P100",
            "P101",
            "P102",
            "P103"
    );

    @Override
    public int getScore(String personUniqueId) {
        if(approvedUniqueIds.contains(personUniqueId)) {
            return 2;
        }
        return 4;
    }
}
