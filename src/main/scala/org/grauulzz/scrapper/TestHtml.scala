package org.grauulzz.scrapper

object TestHtml:
  val htmlExample1: Any =
     s"""
       |<div class="post">
       |  <div class="post-header">
       |    <h2 class="post-title">
       |      <a href="
       |      |https://www.postpatch.com/2018/01/01/post-patch-2018-is-here-and-you-can-too/
       |      |"
       |      |>Post Patch 2018 is here and you can too</a>
       |    </h2>
       |    <div class="post-meta">
       |      <span class="post-date">January 1, 2018</span>
       |      <span class="post-author">by <a href="
       |      |https://www.postpatch.com/author/joshua-m-gonzalez/
       |      |">Joshua M. Gonzalez</a></span>
       |    </div>
       |  </div>
       |""".stripMargin

     val htmlTableExample: Any =
       s"""
         |<table class="table table-striped">
         |  <thead>
         |    <tr>
         |      <th>Name</th>
         |      <th>Age</th>
         |      <th>
         |        <a href="
         |        |https://www.postpatch.com/2018/01/01/post-patch-2018-is-here-and-you-can-too/
         |        |">Post Patch 2018 is here and you can too</a>
         |      </th>
         |    </tr>
         |  </thead>
         |  <tbody>
         |    <tr>
         |      <td>John</td>
         |      <td>20</td>
         |      <td>
         |        <a href="
         |        |https://www.postpatch.com/2018/01/01/post-patch-2018-is-here-and-you-can-too/
         |        |">Post Patch 2018 is here and you can too</a>
         |      </td>
         |    </tr>
         |    <tr>
         |      <td>Jane</td>
         |      <td>21</td>
         |      <td>
         |        <a href="
         |        |https://www.postpatch.com/2018/01/01/post-patch-2018-is-here-and-you-can-too/
         |        |">Post Patch 2018 is here and you can too</a>
         |      </td>
         |    </tr>
         |  </tbody>
         |</table>
         |""".stripMargin

