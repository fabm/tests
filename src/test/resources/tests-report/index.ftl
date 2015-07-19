<#function postSign state>
    <#switch state>
        <#case "ASSERT">
            <#return "ok"/>
        <#case "FAIL">
            <#return "warning-sign"/>
        <#case "ERROR">
            <#return "remove"/>
    </#switch>
</#function>
<#function colorPanelHeader state>
    <#switch state>
        <#case "ASSERT">
            <#return "success"/>
        <#case "FAIL">
            <#return "warning"/>
        <#case "ERROR">
            <#return "danger"/>
    </#switch>
</#function>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${reportTitle}</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-post.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-8">

            <h1>${reportTitle}</h1>

            <hr>
        <#list classes as class>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2 class="panel-title">${class.name}</h2>
                </div>
                <div class="panel-body">
                    <#list class.methods as method>
                        <a name="${class.name}-${method.name}"></a>

                        <div class="panel panel-${colorPanelHeader(method.state)}">
                            <div class="panel-heading">
                                <h2 class="panel-title">${method.name}</h2>
                            </div>
                            <div class="panel-body">
                                <#list method.actions as action>
                                    <#switch action.state>
                                        <#case "ASSERT">
                                            <p><span class="label label-primary"><span
                                                    class="glyphicon glyphicon-time"></span>${action.time}</span> <span
                                                    class="label label-success">${action.name}</span></p>

                                            <p>${action.content}</p>
                                            <#break>
                                        <#case "ERROR">
                                            <p><span class="label label-primary"><span class="glyphicon glyphicon-time"></span>${action.time}</span> <span
                                                    class="label label-danger">error</span></p>

                                            <p>
                                            <pre class="pre-scrollable" style="word-wrap: normal">${action.content}</pre>
                                            </p>
                                            <#break>
                                        <#case "FAIL">
                                            <p><span class="label label-primary"><span class="glyphicon glyphicon-time"></span>${action.time}</span> <span
                                                    class="label label-warning">fail</span></p>

                                            <p>
                                            <pre class="pre-scrollable" style="word-wrap: normal">${action.content}</pre>
                                            </p>
                                            <#break>
                                    </#switch>
                                </#list>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </#list>
        </div>

        <div class="col-md-4">

            <!-- Link tests-->
            <div class="well">
                <h4>Tests</h4>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default" data-example-id="default-media">

                        <#list classes as class>
                            <div class="media">
                                <div class="media-left">
                                    <span class="glyphicon glyphicon-certificate" aria-hidden="true"></span>
                                </div>
                                <div class="media-body">
                                    <p class="media-heading">${class.name}</p>
                                    <#list class.methods as method>
                                        <p>
                                            <span class="glyphicon glyphicon-${postSign(method.state)}" aria-hidden="true"></span>
                                            <a href="#${class.name}-${method.name}">${method.name}</a>
                                        </p>
                                    </#list>
                                </div>
                            </div>
                        </#list>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>

            <!-- Side Widget Well -->
            <div class="well">
                <h4>Summary</h4>
                <ul class="list-group">
                    <li class="list-group-item">
                        <span class="badge">${successes}</span>
                        Successes
                    </li>
                    <li class="list-group-item">
                        <span class="badge">${failures}</span>
                        Failures
                    </li>
                    <li class="list-group-item">
                        <span class="badge">${errors}</span>
                        Errors
                    </li>
                    <li class="list-group-item">
                        <span class="badge">${total}</span>
                        Total
                    </li>
                </ul>
            </div>

        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
