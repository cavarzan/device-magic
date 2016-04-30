package io.github.cavarzan.devicemagic.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Item {

    @Nullable
    public abstract String key();

    @Nullable
    public abstract String value();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Item.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder key(String key);

        public abstract Builder value(String value);

        public abstract Item build();
    }

}
