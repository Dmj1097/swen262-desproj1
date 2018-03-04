package afrs.uiview;

/**
 * Created by Apiazza on 3/1/18.
 */
public class Response {

  private String responseText;

  public Response(String text) {
    this.responseText = text;
  }

  public String getText() {
    return this.responseText;
  }

}
