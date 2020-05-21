package br.ufg.api.ocd.util;

import lombok.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    public static byte[] zip(@NonNull final byte[] anexo) {
        if (!isZipped(anexo)) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                    gzipOutputStream.write(anexo);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Falha ao compactar o conteúdo", e);
            }
        }
        return anexo;
    }

    public static byte[] unzip(@NonNull  final byte[] compressed) {
        if (isZipped(compressed)) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
                GZIPInputStream in = new GZIPInputStream(bis);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) >= 0) {
                    bos.write(buffer, 0, len);
                }
                in.close();
                bos.close();
                return bos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Falha ao descompactar o conteúdo", e);
            }
        }

        return compressed;
    }

    public static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
}
