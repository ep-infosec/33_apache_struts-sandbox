<?xml version="1.0"?>
<html>
<head>
  <title>Struts Flow number guessing game</title>
</head>
<body>

  <h1>Success!</h1>
   
  <h2>The number was: <%= request.getAttribute("random") %></h2>
  
  <h3>It took you <%= request.getAttribute("guesses") %> tries.</h3>
  
  <p><a href="play.do">Play again</a></p>
  
  <a href="../../index.html">Return to index</a>  
</body>
</html>
