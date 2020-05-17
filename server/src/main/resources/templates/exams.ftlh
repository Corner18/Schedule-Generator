<#ftl encoding="UTF-8"/>
<#include "base2.ftlh"/>
<#macro content>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/public/boostrap/bootstrap.css">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/posts.css" type="text/css">
    <meta charset="UTF-8">
    <title>Title</title>
    <body>


    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">

    <div class="cars-horizon">
        <div class="container">

            <div class="row">
                <div class="col-md-9">
                    <#list exams as exam>
                        <div class="row border">
                                <li>Аудитория: ${exam.auditory}" <br>
                                    Предмет: ${exam.subject} <br>
                                    Дата: ${exam.date} <br>
                                    Время: ${exam.time} <br>
                                    <#if exam.group??>
                                        Группа: ${exam.group} <br>
                                    </#if>
                                    Профессор: ${exam.professor}
                                </li>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    </body>
</#macro>
<@main/>