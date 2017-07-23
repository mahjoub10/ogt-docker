declare var $: any;
export class VisibilityUtils {
    handleLoggedUserVisibility(isUserAuthenticated: boolean) {


        if (isUserAuthenticated) {
            $('#btn-Login').hide();
            $('#btn-Logout').show();
            $('#btn-add-car').show();
            
        } else {
        
            $('#btn-Login').show();
            $('#btn-Logout').hide();
            $('#btn-add-car').hide();
        }
        $('#user-loader').hide();

    }

}