package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberdao;

    public MemberRegisterService(MemberDao memberdao){
        this.memberdao = memberdao;
    }

    public Long regist(RegisterRequest req){
        Member member = memberdao.selectByEmail(req.getEmail());
        if(member != null){
            throw new DuplicateMemberException("dup emaol" + req.getEmail());
        }

        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now());
        memberdao.insert(newMember);
        return newMember.getId();
    }

}
