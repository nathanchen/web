<#import "/include/layout.ftl" as layout/>

<@layout.my_project>
<div class="col-sm-8">
    <div id="2016">
        <h2>2016年
            <small> - 重写网站、手机触屏版、微信商城</small>
        </h2>
        <div class="slide">
            <h4>项目目标</h4>
            <p>- 使用SpringMVC重写所有网站系统</p>
            <p>- 为商城建立一套适宜的系统体系架构</p>
        </div>
        <div class="slide">
            <h4>项目最终架构</h4>
            <p>在制定商城的整体架构时，我的原则是不画蛇添足，不过分设计，不为了炫技而做不必要的系统；但与此同时，要让项目成员能学习到新东西，能让今后的开发流程变得更有趣。</p>
            <p>因此，在设计最后框架的过程中，我参考了<a href="http://www.infoq.com/cn/articles/suning-product-details-system-architecture-design" rel="nofollow">苏宁</a>、<a href="http://jinnianshilongnian.iteye.com" rel="nofollow">京东</a>两家的框架，融合了`<a href="https://wanqu.co/b/7/2015-05-24-behind-the-scenes.html" rel="nofollow">湾区日报</a>`的系统管理和代码上线流程，参考了<a href="http://tech.meituan.com/improving-git-flow_squashing-commits.html" rel="nofollow">美团点评</a> 的Git Flow，最后整理出了这套框架和流程。</p>
            <img src="${__static_server__}/image/pages/projects/2016/all-arch.jpeg">
        </div>
        <div class="slide">
            <h4>项目模块组成及分工</h4>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>模块名称</th>
                        <th>模块注解</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>BearyChat</td>
                        <td>类似于Slack的软件, 主要用来接收服务状态信息、充当对话机器人等</td>
                    </tr>
                    <tr>
                        <td>Backbone系统</td>
                        <td>自制系统模块, 主要用来执行定时检查更新Redis数据、跑页面可用性测试等</td>
                    </tr>
                    <tr>
                        <td>Admin系统</td>
                        <td>自制系统模块, 主要预处理服务器日志供分析、提取错误日志进行报警等</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="slide">
            <h4>项目模块分解</h4>
            <p>- 项目管理模块</p>
            <img src="${__static_server__}/image/pages/projects/2016/proj-manage.jpeg">
            <img src="${__static_server__}/image/pages/projects/2016/proj-go-online.jpeg">
            <hr/>
            <p>- 项目页面缓存模块</p>
            <img src="${__static_server__}/image/pages/projects/2016/proj-backbone.jpeg">
            <p>电商网站上的很多页面，所有用户在一定的时间段看到的内容都是一样的，所以没有必要每次用户请求的时候都动态生成一遍这样的页面。</p>

            <p>出于简化开发难度的关系，我们最初的做法是在每一台<code>Tomcat</code>服务器层上加一个<code>EhCache</code>缓存，利用其<code>SimplePageCachingFilter</code>缓存html页面。但是这种方式带来了分布式服务器缓存不一致和资源的浪费，在解决这两个问题的时候，我们发现<code>EhCache</code>的方式增大了维护的成本，所以我们决定不再使用这个方案。
            </p>

            <p>然后我们就采用了<code>Nginx</code>上加<code>OpenResty</code>的解决方法，网站上的大多页面会被缓存到<code>Redis</code>中，每次用户请求到达<code>Nginx</code>上的时候，就直接找<code>Redis</code>缓存中的页面，配合着我们的<code>Backbone</code>系统，<code>Redis</code>的数据会定时的进行更新。
            </p>

            <p>再接下来，我们对缓存在<code>Redis</code>中的缓存颗粒度进行了优化，不再以页面级别进行缓存，而是缓存页面中的细分模块。</p>
            <hr/>
            <p>- 项目日志及监控模块</p>
            <img src="${__static_server__}/image/pages/projects/2016/proj-admin.jpeg">
            <img src="${__static_server__}/image/pages/projects/2016/proj-elk.jpeg">
        </div>
        <div class="slide">
            <h4>总结</h4>
            <p>在最新的框架下，整个商城代码相关的流程及性能都得到的优化：</p>
            <p>- 每个上线的代码都经过他人的审核，虽然审核不能保证没有bug，但是可以减少低级错误的发生，并且统一大家的代码格式及编程习惯。</p>
            <p>- 程序员也参与到代码上线的流程中来，加快了测试环境代码上线的节奏的同时，通过使用自动化的上线流程，减小了上线可能带来的风险。</p>
            <p>- 强调了测试的重要性。代码的每次上线都必须通过代码测试和前端测试，没有适量的测试案例就会导致提交的代码在自动化测试过程中得不到任何的反馈。</p>
            <p>- 更快的定位bug。借助ELK，程序员可以更快的查看到错误日志，甚至可以筛选出导致错误的一系列操作。</p>
            <p>- 借助OpenResty，Tomcat服务器的压力被大大地减少，Nginx的使用率被提高了。</p>
        </div>
        <div class="slide">
            <h4>项目今后发展</h4>
            <p>在商城最新的框架上，我们预留了下一步优化的切入点和原始积累。这就是用户的数据。用户的每一次请求都会被记录下来, 并且我们给每个用户都加了唯一标识用于区分。目前用户的日志仅仅在ELK上进行展示, 帮助发现和重现bug。</p>
            <p>按照规划, 这些日志信息会被进一步加以利用。</p>
            <p>- 首先是生成用户的访问路径: 我们需要知道用户从哪个页面着陆, 从哪个页面离开, 期间又访问了哪些页面。</p>
            <p>- 其次是推荐系统: 当我们有了用户的访问路径, 知道了用户购买了什么商品, 通过简单的聚类, 我们就可以把用户划分成不同的群体, 有针对性的对每个用户进行推荐。</p>
            <p></p>
            <p>当然还有一些遗憾和我们想得不够清楚的地方，一个最直接的地方是OpenResty这里所使用的数据库问题，我们最终将会把Redis切换回MySQL，因为这里的数据库主要是缓存html页面的相关片段，归属哪个url，缓存的时间是什么等等各式各样的信息，Redis适合多读少写的情况，可是它基于KV的数据结构增加使用代码提取数据的复杂度，所以我们又准备牺牲一些性能，切换回关系型数据库，可能应该考虑下MongoDB？</p>
        </div>
    </div>
</div>
</@layout.my_project>
