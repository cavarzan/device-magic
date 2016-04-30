package io.github.cavarzan.devicemagic.model;


import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Download {

    @Nullable
    public abstract List<String> uids();

    @Nullable
    public abstract Item item();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Download.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder uids(List<String> uids);
        public abstract Builder item(Item item);

        public abstract Download build();
    }

}
