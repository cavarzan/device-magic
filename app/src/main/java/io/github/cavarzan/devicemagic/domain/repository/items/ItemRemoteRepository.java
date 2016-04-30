package io.github.cavarzan.devicemagic.domain.repository.items;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import io.github.cavarzan.devicemagic.BuildConfig;
import io.github.cavarzan.devicemagic.domain.entity.DownloadEntity;
import io.github.cavarzan.devicemagic.domain.entity.DownloadsEntity;
import io.github.cavarzan.devicemagic.model.Item;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class ItemRemoteRepository {

    private final OkHttpClient client;

    public ItemRemoteRepository(OkHttpClient client) {
        this.client = client;
    }

    public Observable<DownloadsEntity> findAll() {
        return Observable.fromCallable(() -> {
            Request builders = new Request.Builder()
                    .url(BuildConfig.API_ENDPOINT_INTERNAL)
                    .get()
                    .build();
            Response execute = client.newCall(builders).execute();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(execute.body().byteStream());
//            Document document = builder.parse(BuildConfig.API_ENDPOINT_INTERNAL); // Could be done directly with DocumentBuilder. But I'l use OkHttp to perform a "normal" case.
            NodeList downloads = document.getElementsByTagName("item");

            List<String> ids = new ArrayList<>();

            for (int i = 0; i < downloads.getLength(); i++) {
                Node item = downloads.item(i);
                if ("item".equals(item.getNodeName())) {
                    ids.add(item.getFirstChild().getNodeValue());
                }
            }

            DownloadsEntity entity = new DownloadsEntity();
            entity.item = ids;
            return entity;
        });
    }

    public Observable<DownloadEntity> findOne(String uid) {
        return Observable.fromCallable(() -> {
            Request builderHttp = new Request.Builder()
                    .url(BuildConfig.API_ENDPOINT_INTERNAL + "downloads/" + uid)
                    .get()
                    .build();
            Response execute = client.newCall(builderHttp).execute();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(execute.body().byteStream());
            NodeList childNodes = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if ("item".equals(item.getNodeName())) {
                    NodeList childNodesEntity = item.getChildNodes();
                    Item.Builder entityBuilder = Item.builder();
                    for (int j = 0; j < childNodesEntity.getLength(); j++) {
                        Node entity = childNodesEntity.item(j);
                        if ("key".equals(entity.getNodeName())) {
                            entityBuilder.key(entity.getFirstChild().getNodeValue());
                        }
                        if ("value".equals(entity.getNodeName())) {
                            entityBuilder.value(entity.getFirstChild().getNodeValue());
                        }
                    }

                    DownloadEntity entity = new DownloadEntity();
                    entity.item = entityBuilder.build();
                    return entity;
                }
            }
            return null;

        });
    }

}
