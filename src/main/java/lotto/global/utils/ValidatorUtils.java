package lotto.global.utils;

import lotto.global.constant.CommonCode;
import lotto.module.domain.Lotto;

/**
 * 유효성체크
 *
 * @author yoonho
 * @since 2023.11.08
 */
public class ValidatorUtils {

    /**
     * Integer 형변환 체크
     *
     * @param input {@link String}
     * @return {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public static Integer parseInt(String input) {
        try {
            return Integer.parseInt(input);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수값이 아닙니다.");
        }
    }

    /**
     * 최소단위 체크
     *
     * @param amount {@link Integer}
     * @param unit {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public static void validateUnit(int amount, int unit) {
        if(amount % unit != 0) {
            throw new IllegalArgumentException("[ERROR] 최소 단위에 부합하지 않습니다.");
        }
    }

    /**
     * 허용범위 체크
     *
     * @param number {@link Integer}
     * @param min {@link Integer}
     * @param max {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public static void validateRange(int number, int min, int max) {
        if(number < min || number > max) {
            throw new IllegalArgumentException("[ERROR] 허용된 범위에 포함되지 않습니다.");
        }
    }

    /**
     * 로또구매금액 검증
     *
     * @param input {@link String}
     * @return {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public static Integer validateBuyAmount(String input) {
        Integer buyAmount = ValidatorUtils.parseInt(input);
        ValidatorUtils.validateUnit(buyAmount, CommonCode.BUY_MIN_UNIT);

        return buyAmount;
    }

    /**
     * 보너스번호 검증
     *
     * @param winLotto {@link Lotto}
     * @param input {@link String}
     * @return {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public static Integer validateBonusNumber(Lotto winLotto, String input) {
        Integer bonusNumber = ValidatorUtils.parseInt(input);
        ValidatorUtils.validateRange(bonusNumber, CommonCode.MIN_LOTTO_NUM, CommonCode.MAX_LOTTO_NUM);

        if(winLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호에 포함되지 않은 숫자여야 합니다.");
        }

        return bonusNumber;
    }
}
