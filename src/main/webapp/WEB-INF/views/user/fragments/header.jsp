<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> --%>
<header ng-controller="topBarCtrl">
  <!-- Fixed navbar -->
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"><h4>AIIMS Research Directory</h4></a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link" ng-class="(url.includes('home'))?'active':''" href="home"><i class="fa fa-home"></i> Home</a>
          </li>
          <sec:authorize access="hasAnyRole('ROLE_User')">
		    <li class="nav-item">
               <a class="nav-link" ng-class="(url.includes('edit-profiles'))?'active':''" href="edit-profiles"><i class="fa fa-file"></i> Edit Profiles</a>
            </li>
		  </sec:authorize>
          
          
          <sec:authorize access="hasAnyRole('ROLE_Reviewer')">
		    <li class="nav-item">
               <a class="nav-link" ng-class="(url.includes('review-profiles'))?'active':''" href="review-profiles"><i class="fa fa-file"></i> Review Profiles</a>
            </li>
		  </sec:authorize>
        </ul>
        <form class="d-flex">
          
          <a class="nav-link light-link" style="color:white;" href="../logout">Logout</a>
        </form>
      </div>
    </div>
  </nav>
</header>