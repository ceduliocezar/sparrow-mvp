package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.SummaryEntity;
import com.cedulio.sparrow.domain.model.Summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SummaryEntityDataMapper {

    public Summary transform(SummaryEntity summaryEntity) {
        Summary summary = null;

        if (summaryEntity != null) {
            summary = new Summary();
            summary.setCloseDate(summaryEntity.getCloseDate());
            summary.setDueDate(summaryEntity.getCloseDate());
            summary.setInterest(summaryEntity.getInterest());
            summary.setMinimumPayment(summaryEntity.getMinimumPayment());
            summary.setOpenDate(summaryEntity.getOpenDate());
            summary.setPaid(summaryEntity.getPaid());
            summary.setPastBalance(summaryEntity.getPastBalance());
            summary.setTotalBalance(summaryEntity.getTotalBalance());
            summary.setTotalCumulative(summaryEntity.getTotalCumulative());
        }

        return summary;
    }

    public List<Summary> transform(Collection<SummaryEntity> summaryEntityCollection) {

        if (summaryEntityCollection == null) {
            return Collections.emptyList();
        }

        List<Summary> summaryList = new ArrayList<>();
        Summary summary;

        for (SummaryEntity summaryEntity : summaryEntityCollection) {
            summary = transform(summaryEntity);
            if (summary != null) {
                summaryList.add(summary);
            }
        }
        return summaryList;
    }
}
