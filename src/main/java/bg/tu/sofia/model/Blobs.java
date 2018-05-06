package bg.tu.sofia.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
public class Blobs {
    private Integer id;
    private String filename;
    private String contentType;
    private Integer filesize;
    private byte[] content;
    private Date createdDate;

    @Id
    @GeneratedValue(generator = "blobs_id_seq")
    @SequenceGenerator(sequenceName = "blobs_id_seq", name = "blobs_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "filesize")
    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    @Basic
    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Basic
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Transient
    public String getEncodedContent() {
        return Base64.encode(this.content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blobs blobs = (Blobs) o;
        return Objects.equals(id, blobs.id) &&
                Objects.equals(filename, blobs.filename) &&
                Objects.equals(contentType, blobs.contentType) &&
                Objects.equals(filesize, blobs.filesize) &&
                Arrays.equals(content, blobs.content) &&
                Objects.equals(createdDate, blobs.createdDate);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, filename, contentType, filesize, createdDate);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
