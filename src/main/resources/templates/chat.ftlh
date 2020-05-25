<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/comment.js","/js/jquery-3.3.1.slim.min.js", "/js/popper.min.js", "/js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["/css/main.css","/css/style.css" ,"/css/bootstrap.min.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <div class="card mb-3 flex flex-column box flex-v-center" style="text-align:center">
    </div>
    <div>
    </div>
    <button type="button" class="button-sign btn btn-secondary" data-toggle="modal" data-target="#modal-form-autorization">Add comment</button>
    <div id="messages">
        <div id="0"></div>
    </div>
    <div class="modal fade" id="modal-form-autorization" tabindex="-1" role="dialog" aria-labelledby="form-title" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="form-title">Message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <form >
                        <div>
                            <#if error!false >
                                <p style="color:red">Eror !</p>
                            </#if>
                        </div>

<#--                        <#if _csrf??>-->
<#--                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>-->
<#--                        </#if>-->
                        <div class="form-group">
                            <label for="exampleInputEmail1" class="flex flex-v-center ">Comment</label>
                            <input type="text" name="text" class="form-control" id="exampleInputEmail1" aria-describedby="text"  autofocus >
                        </div>
                        <button type="button" class="send btn btn-secondary" name="email"  onclick="addComment(this.form)">Add comment</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.renderWith>