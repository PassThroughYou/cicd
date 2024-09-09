package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.board.Board;
import dongwoongkim.crud.domain.member.Member;
import dongwoongkim.crud.dto.board.BoardEditRequestDto;
import dongwoongkim.crud.dto.board.BoardRequestDto;
import dongwoongkim.crud.dto.board.BoardResponseDto;
import dongwoongkim.crud.exception.BoardNotFoundException;
import dongwoongkim.crud.exception.MemberNotFoundException;
import dongwoongkim.crud.exception.WriteFailureException;
import dongwoongkim.crud.repository.BoardRepository;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<BoardResponseDto> findAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(b -> BoardResponseDto.toDto(b));
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto save(BoardRequestDto boardRequestDto, String nickName) {
        Member member = memberRepository.findByNickname(nickName).orElseThrow(MemberNotFoundException::new);
        boardRequestDto.setMember(member);
        Board board = boardRepository.save(Board.toEntity(boardRequestDto));
        if (board == null) {
            throw new WriteFailureException();
        }
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto update(Long id, BoardEditRequestDto boardEditRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateBoard(boardEditRequestDto);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
    }

}
