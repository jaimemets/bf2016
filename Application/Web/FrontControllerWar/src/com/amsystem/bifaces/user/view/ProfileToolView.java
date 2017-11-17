package com.amsystem.bifaces.user.view;

import com.amsystem.bifaces.user.UserProfileOperation;
import com.amsystem.bifaces.user.model.Profile;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.util.NodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: ProfileToolView.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 16/10/2017.
 */

@ViewScoped
@ManagedBean(name = "profileToolView")
public class ProfileToolView implements Serializable {

    //Atributo usado para la impresion de logs
    private static final Logger log = LogManager.getLogger(UserToolView.class.getName());

    //Raiz del arbol de perfiles
    private TreeNode root;

    //Nodo seleccionado en el arbol de perfiles
    private TreeNode selectedProfileNode;

    //Lista de todos los perfiles en el sistema
    private List<Profile> allProfile;

    //Lista de usuarios pertenecientes a un perfil seleccionado
    private List<User> allUserProfile;

    //Lista de usuarios seleccionados en el campo auto-completar
    private List<String> userList;

    //Perfil seleccionado de la tabla perfiles-descripcion
    private Profile selectedProfile;

    //Usuario seleccionado de la tabla usuario-perfil
    private User selectedUser;

    //Campo nombre perfil para agregar al arbol
    private String nameProfile;

    @ManagedProperty("#{userProfileOperation}")
    private UserProfileOperation userProfileOperation;

    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    @PostConstruct
    public void init() {
        allProfile = userProfileOperation.getAllProfile();
        allUserProfile = new ArrayList<>();
        root = userProfileOperation.createTreeProfile();
    }


    /**
     * Agrega un nuevo perfil al arbol.
     */
    public void addProfile() {
        log.debug("Agregando el Perfil: " + nameProfile);
        Profile profile = new Profile(nameProfile.toUpperCase());
        userProfileOperation.addProfile(getFirstChild(), profile);
        setNameProfile(null);

    }


    /**
     * Elimina el perfil seleccionado del arbol
     * TODO: Funcionalidad y validaciones al momento de eliminar el perfil seleccionado
     */
    public void deleteProfile() {
        log.debug("Eliminando el perfil: " + selectedProfile.getName());
        userProfileOperation.deleteProfile(selectedProfile);
        setSelectedProfile(null);

    }


    /**
     * Completa los usuarios que coincidadn con el parametro recibido y que a su vez no se encuentre asociado
     * al perfil seleccionado
     *
     * @param query
     * @return Lista de usuarios
     */
    public List<User> completeUser(String query) {
        List<User> allUsers = userProfileOperation.getAllUser();
        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            if (!allUserProfile.contains(user) && user.getFirstName().toLowerCase().contains(query)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }


    /**
     * Asocia al perfil seleccionado en el arbol de perfiles, un(os) usuario(s) obtenidos en el campo autocompletar
     */
    public void onAddUserToProfile() {
        userProfileOperation.addUserToProfile(userList, (Profile) selectedProfileNode.getData());
    }


    /**
     * Desasocia perfil seleccionado en el arbol de perfiles, el usuario seleccionado en la tabla usuario-perfiles
     */
    public void onDeleteUserToProfile() {
        Profile profile = (Profile) selectedProfileNode.getData();
        userProfileOperation.deleteUserToProfile(selectedUser, profile);
        allUserProfile.clear();
        allUserProfile.addAll(userProfileOperation.getAllUserByProfile(profile));

    }


    /**
     * Carga la tabla usuario-perfiles de acuerdo al perfile seleccionado del arbol de perfiles
     *
     * @param event
     */
    public void onNodeSelect(NodeSelectEvent event) {
        allUserProfile.clear();
        if (event.getTreeNode().getType().equalsIgnoreCase(NodeType.PROPERTY.getLabel())) {
            allUserProfile.addAll(userProfileOperation.getAllUserByProfile((Profile) event.getTreeNode().getData()));
        }
        log.debug("allUserProfile size: " + allUserProfile.size());

    }

    /**
     * Actualiza la descripccion del perfil seleccionado en la tabla perfiles-descripcion
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
        Profile profile = (Profile) event.getObject();
        userProfileOperation.updateProfile(profile);
    }

    /**
     * Retorna el primer hijo del arbol de perfiles
     * @return
     */
    private TreeNode getFirstChild() {
        return root.getChildren().get(0);
    }

    public List<User> getUserProfile() {
        return allUserProfile;
    }

    public List<Profile> getAllProfile() {
        return allProfile;
    }

    public void setAllProfile(List<Profile> allProfile) {
        this.allProfile = allProfile;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public UserProfileOperation getUserProfileOperation() {
        return userProfileOperation;
    }

    public void setUserProfileOperation(UserProfileOperation userProfileOperation) {
        this.userProfileOperation = userProfileOperation;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedProfileNode() {
        return selectedProfileNode;
    }

    public void setSelectedProfileNode(TreeNode selectedProfileNode) {
        this.selectedProfileNode = selectedProfileNode;
    }

    public List<User> getAllUserProfile() {
        return allUserProfile;
    }

    public void setAllUserProfile(List<User> allUserProfile) {
        this.allUserProfile = allUserProfile;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }
}
