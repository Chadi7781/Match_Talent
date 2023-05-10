/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;
import com.mycompany.service.ServiceUtilisateur;

/**
 *
 * @author Lenovo
 */
public class ModifierUtilisateur extends BaseForm {
    
    Form current;
    public ModifierUtilisateur(Resources res , Utilisateur r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField objet = new TextField(r.getName(), "Name" , 20 , TextField.ANY);
        TextField description = new TextField(r.getEmail(), "Email" , 20 , TextField.ANY);
               TextField etat = new TextField(String.valueOf(r.getContact()) , "Contact" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox etatCombo = new ComboBox();
        
        
        
        
        
        objet.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        etat.setUIID("NewsTopLine");
        
        objet.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setName(objet.getText());
           r.setEmail(description.getText());
           r.setContact(etat.getText());
           
       
       //appel fonction modfier reclamation men service
       
       
       ServiceUtilisateur.getInstance().EditUser(r.getName(),r.getEmail(),r.getContact());// if true
           new ListUtilisateurs(res).show();
       
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListUtilisateurs(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(objet),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}