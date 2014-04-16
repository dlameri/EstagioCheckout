<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Login</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
		
		<script>
  window.fbAsyncInit = function() {
  FB.init({
    appId      : '720511221302083',
    status     : true, // check login status
    cookie     : true, // enable cookies to allow the server to access the session
    xfbml      : true  // parse XFBML
  });

  // Here we subscribe to the auth.authResponseChange JavaScript event. This event is fired
  // for any authentication related change, such as login, logout or session refresh. This means that
  // whenever someone who was previously logged out tries to log in again, the correct case below 
  // will be handled. 
  FB.Event.subscribe('auth.authResponseChange', function(response) {
    // Here we specify what we do with the response anytime this event occurs. 
    if (response.status === 'connected') {
      // The response object is returned with a status field that lets the app know the current
      // login status of the person. In this case, we're handling the situation where they 
      // have logged in to the app.
      testAPI();
    } else if (response.status === 'not_authorized') {
      // In this case, the person is logged into Facebook, but not into the app, so we call
      // FB.login() to prompt them to do so. 
      // In real-life usage, you wouldn't want to immediately prompt someone to login 
      // like this, for two reasons:
      // (1) JavaScript created popup windows are blocked by most browsers unless they 
      // result from direct interaction from people using the app (such as a mouse click)
      // (2) it is a bad experience to be continually prompted to login upon page load.
      FB.login();
    } else {
      // In this case, the person is not logged into Facebook, so we call the login() 
      // function to prompt them to do so. Note that at this stage there is no indication
      // of whether they are logged into the app. If they aren't then they'll see the Login
      // dialog right after they log in to Facebook. 
      // The same caveats as above apply to the FB.login() call here.
      FB.login();
    }
  });
  };

  // Load the SDK asynchronously
  (function(d){
   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
   if (d.getElementById(id)) {return;}
   js = d.createElement('script'); js.id = id; js.async = true;
   js.src = "//connect.facebook.net/en_US/all.js";
   ref.parentNode.insertBefore(js, ref);
  }(document));

  // Here we run a very simple test of the Graph API after login is successful. 
  // This testAPI() function is only called in those cases. 
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Good to see you, ' + response.name + '.');
    });
  }
</script>

<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->
		<meta charset="utf-8">
	</head>
	<body>
		<div id="wrapper">	
			<div id="header">
				<div id="line"></div>
				<div id="header-content">
					<figure>
						<a href="#"><img src="<c:url value="/resources/images/logo.png" />" alt="imagem do produto x"/></a> 
					</figure>
					<h2 class="logo-title">Loja Virtual</h2>
					<h5 class="logo-subtitle">Breve descrição da loja</h5> 
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">Categorias</a>
							<ul>
								<li><a href="#">Categoria 1</a></li>
								<li><a href="#">Categoria 2</a></li>
								<li><a href="#">Categoria 3</a></li>
								<li><a href="#">Categoria 4</a></li>
							</ul> 
						</li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Fale Conosco</a></li>
					</ul>		
				</div>			
			</div><!-- #header -->		
			<div id="content">
				<div id="content-container">
					<section class="login">
	    				<h3 class="title">Faça login com o facebook</h3>
						<fb:login-button class="facebook-button" show-faces="true" width="200" max-rows="1"></fb:login-button>
	
	    				<h3 class="title">Faça seu Login sem o facebook</h3>
						<form class="loginForm" action="" autocomplete="off" method="POST">
							<div class="input-group">
								<input type="text" class="form-control" name="username" placeholder="email address">
							</div>
							<span class="help-block"></span>
							
							<form:form action="emptyCart" method="post" commandName="cart">
					            <span>esvaziar carrinho de compras</span>
								<input class="cartItemInput" value="esvaziar" type="submit">
					        </form:form>
							
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input  type="password" class="form-control" name="password" placeholder="Password">
							</div>
							<button class="login-button" type="submit">Login</button>
						</form>
	
					</section>
				</div>        
			</div><!-- #content -->
			<div id="footer">
				<div id="footer-content">
					<a href="#"> Powered by Estagiários </a>
				</div>
			</div><!-- #footer -->
			</div><!-- #wrapper -->
	</body>
</html>