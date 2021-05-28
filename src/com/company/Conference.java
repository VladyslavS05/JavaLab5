package com.company;

import java.io.Serializable;

public abstract class Conference implements ConferenceFunctions, Serializable {
    String name, place;

    protected abstract void AverageNumberMembers();
    protected abstract void LargestNumberMembers();
}
