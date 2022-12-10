<%--
   Copyright 2004-2005 The Apache Software Foundation.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
   
       http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
  
   $Header:$
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
    <head>
        <base href="<%= request.getScheme() %>://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getRequestURI() %>">
    </head>

    <body>
        <h3>Choose Airport (nested page flow)</h3>
        <form action="doSearch.do" method="POST">
            Airport city or name:
            <input name="{actionForm.searchText}"/>
            <%--<netui:error key="searchText"/>--%>
            <br/>
            <input type="submit" value="OK"/>
            <input type="submit" name="actionOverride:cancelSearch" value="cancel"/>
        </form>
    </body>
</html>
