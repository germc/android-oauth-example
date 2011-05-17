<!DOCTYPE HTML>

<html>

  <!-- Copyright 2011 Mark Wyszomierski -->
  
  <head> 
    <script type="text/javascript"> 
    
        function onBodyLoad() {
            // We would have wrote this object into the document if oauth
            // was successful.
            var element = document.getElementById("token");
            if (element != null) {
                // In our android application, we named the javscript interface
                // object 'javascriptAccessor', so make sure it has the same 
                // name here.
                window.javascriptAccessor.getOAuthToken(element.innerHTML); 
            }
        } 

    </script> 
  </head> 
  
  <body onload="onBodyLoad()">
    <p>Hi there.</p>
    
    <?php
    
    $code = $_GET['code'];
    if (isset($code)) {
        
        // After we get the code from foursquare, make the following http call to
        // get back the final access token. It will be a JSON object in the form
        // of: { access_token: ACCESS_TOKEN }.
        $url = 
          "https://foursquare.com/oauth2/access_token" .
            "?client_id=" . "your client id" .
            "&client_secret=" . "your client secret" . 
            "&grant_type=authorization_code" .
            "&redirect_uri=" . "your callback url" .
            "&code=" . $code;
        $jsonResponse = json_decode(file_get_contents($url));
        
        // Write out the access_token value into the document. The android client
        // can pick it up on document load using the javascript interface method.
        echo "<div id='token' style='display:none;' >" . $jsonResponse->{'access_token'} . "</div>";
        
    }
    else {
        echo "No 'code' param found!";
    }
    ?>
    <br />
    <br />
    
  </body>

</html>