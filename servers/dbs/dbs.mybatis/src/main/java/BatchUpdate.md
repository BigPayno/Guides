update t_agent_import_failed_report
    set phone_number = case id
        <foreach collection="list" item="dto">
            when #{dto.id} then #{dto.phoneNumber,typeHandler = com.qingqing.common.mybatis.handler.ShadowStringTypeHandler}
        </foreach>
    end
    where id in
        <foreach collection="list" item="dto" open="(" separator="," close=")">
            #{dto.id}
        </foreach>