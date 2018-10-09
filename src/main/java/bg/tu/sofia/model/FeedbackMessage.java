package bg.tu.sofia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "feedback_message", schema = "public")
public class FeedbackMessage implements Serializable {
  private Integer id;
  private String title;
  private String text;
  private String userEmail;

  @Id
  @GeneratedValue(generator = "feedback_message_id_seq")
  @SequenceGenerator(sequenceName = "feedback_message_id_seq", name = "feedback_message_id_seq", schema = "public", allocationSize = 1, initialValue = 1)
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Basic
  @Column(name = "user_email")
  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FeedbackMessage that = (FeedbackMessage) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(text, that.text) &&
            Objects.equals(userEmail, that.userEmail);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, title, text, userEmail);
  }
}
