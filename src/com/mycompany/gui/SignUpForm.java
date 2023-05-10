
  
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author chédy
 */
public class SignUpForm extends BaseForm {
    
       TextField username;
       TextField password,
       confirmPassword,emaill;
       ComboBox<String> role;
       String token ; 
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
       // tb.addCommandToRightBar(null, res.getImage("backmenu.png"), e -> previous.showBack());
        setUIID("IMGLogin");
       add(BorderLayout.NORTH,  new Label(res.getImage("s4s_1.png"),"LogoLabel"));

        username = new TextField("", "Username", 20, TextField.ANY);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        emaill = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        Vector<String> VectorRole = new Vector();
        VectorRole.add("DEMANDEUR");
        VectorRole.add("OFFREUR");
        
  //      role = new ComboBox<>(VectorRole);
        
        
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        emaill.setSingleLineTextArea(false);
        Button fb= new Button("Se connecter avec facebook");
        
          Vector<String> VectorNiveau = new Vector();
        VectorNiveau.add("Client");
        VectorNiveau.add("Societe");
    
        
        role = new ComboBox<>(VectorNiveau);
    
  
        
        Button next = new Button("Ajouter");
        Button signIn = new Button("Se Connecter");
        next.addActionListener((register) -> {
                String url = "http://localhost/register?name="+username.getText().toString()+"&password="+password.getText().toString()+"&email="+emaill.getText().toString()+"&roles="+role.getSelectedItem().toString();
                ConnectionRequest req = new ConnectionRequest(url, false);
                //req.setUrl(url);
                
                       if(username.getText().equals(" ") && password.getText().equals(" ") && emaill.getText().equals(" ")) {
                            Dialog.show("Erreur", "Veuillez remplir les champs", "Ok", null);

                       }
                req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {    
      next.requestFocus();
                               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
                                    Dialog.show("Success", "Inscription avec success", "OK",null);

        ipDlg.dispose();
               new SignInForm(res).show();

                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueue(req);
            
        });
        
        
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Avez vous deja un compte?");
        signIn.addActionListener(e -> new SignInForm(res).show());
        
        Container content = BoxLayout.encloseY(
                new Label("", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(emaill),
                createLineSeparator(),
                role
                
               

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                
                next, fb,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        //267451283658593
        //fda59afbe09f1dedbc70bfddd135618e
         fb.addActionListener((e) -> {
            String clientId = "579677342755347";
            String redirectURI = "https://localhost";
            String clientSecret = "a8eafbd89fb340aa8090fdee50eec22a";
            Login fbe = FacebookConnect.getInstance();
            fbe.setClientId(clientId);
            fbe.setRedirectURI(redirectURI);
            fbe.setClientSecret(clientSecret);
            //Sets a LoginCallback listener
            fbe.setCallback(new LoginCallback() {
                @Override
                public void loginSuccessful() {
                    try {
                        token = fbe.getAccessToken().getToken();
                        System.out.println(token);
                        testLoadJSONUsingJSONParser(token);
                    } catch (Exception e) {
                    }

                }

                @Override
                public void loginFailed(String errorMessage) {
                    Dialog.show("No!", "it did not work!", "sad", null);
                }
            });
            //trigger the login if not already logged in
            if (!fbe.isUserLoggedIn()) {
                fbe.doLogin();
                System.out.println("connected");
            } else {
                //get the token and now you can query the facebook API
                token = fbe.getAccessToken().getToken();
                System.out.println(token);

            }

        });

       
        
    }
    
    public void testLoadJSONUsingJSONParser(String token) {

        ConnectionRequest req = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {
            //super.readResponse(input);

                InputStreamReader reader = new InputStreamReader(input);
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(reader);

                String nom = (String) response.get("first_name");
                String prenom = (String) response.get("last_name");
                String sexe = (String) response.get("gender");
                String email = (String) response.get("email");
                String birthday = (String) response.get("birthday");
               
                Map<String, Object> pictureimage = new LinkedHashMap<String, Object>();
                pictureimage = (Map<String, Object>) response.get("picture");
                Map<String, Object> dataimage = new LinkedHashMap<String, Object>();
                dataimage = (Map<String, Object>) pictureimage.get("data");
                String urlImage = (String) dataimage.get("url");

                System.out.println(nom + "" + prenom + ""  + email);
                username.setText(nom+""+prenom);
                emaill.setText(email);
                
                        }

        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl("https://graph.facebook.com/v2.9/me?fields=id,email,last_name,first_name,birthday,gender,picture.width(500).height(500)&access_token=" + token);

        NetworkManager.getInstance().addToQueue(req);

    }

    
}
