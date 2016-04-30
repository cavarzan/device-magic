package io.github.cavarzan.devicemagic.domain.entity.converter;

import io.github.cavarzan.devicemagic.domain.entity.DownloadEntity;
import io.github.cavarzan.devicemagic.domain.entity.DownloadsEntity;
import io.github.cavarzan.devicemagic.model.Download;

public class DownloadConverter {

    public static Download wrap(DownloadEntity entity) {
        if (entity == null) return null;
        return Download.builder()
                .item(entity.item)
                .build();
    }

    public static DownloadEntity unwrap(Download model) {
        DownloadEntity entity = new DownloadEntity();
        entity.item = model.item();
        return entity;
    }

    public static Download wrapDownloads(DownloadsEntity entity) {
        if (entity == null) return null;
        return Download.builder()
                .uids(entity.item)
                .build();
    }

    public static DownloadsEntity unwrapDownloads(Download model) {
        DownloadsEntity entity = new DownloadsEntity();
        entity.item = model.uids();
        return entity;
    }

}
